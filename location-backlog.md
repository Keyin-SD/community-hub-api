# Location Feature — Backlog Cards

---

### 1. Create Location entity
Create a `Location` entity with `locationId` (auto-generated PK) and `name` fields. Add `LocationRepository` extending `JpaRepository`. Place in `com.communityhub.location` package.

**Acceptance:** Entity exists, table is created on app startup via `ddl-auto=update`.

---

### 2. Add bidirectional relationship between Resource and Location
Add `@ManyToOne` + `@JoinColumn` on `Resource` pointing to `Location`. Add `@OneToMany(mappedBy = "location")` on `Location` pointing back to its resources. Remove the old `resourceLocation` String field.

Use `@JsonIgnore` on the `Location.resources` list to prevent infinite serialization — DTOs (card #8) will handle what each side exposes.

**Acceptance:** `resource` table has a `location_id` FK column. Relationship is navigable from both sides in code.

---

### 3. Auto-resolve location on resource create/update
In `ResourceService`, when creating or updating a resource, look up the location by name (case-insensitive). If it exists, reuse it. If not, create a new one and attach it.

**Acceptance:** Creating two resources with the same location name results in one `Location` row, not two.

---

### 4. Create GET /api/locations endpoint
Add `LocationController` with:
- `GET /api/locations` — returns all locations (powers the frontend dropdown).
- `GET /api/locations/{id}` — returns a single location with its list of resources (location detail page).

**Acceptance:** Both endpoints work and show in Swagger docs. The detail endpoint includes the location's resources.

---

### 5. Update search-by-location to use Location entity
Refactor the existing `searchByLocation` endpoint and repository query to filter by `location.name` instead of the old `resourceLocation` string.

**Acceptance:** `GET /api/resources/searchByLocation/{location}` still works, now querying the joined Location table.

---

### 6. Migrate existing resourceLocation data to Location table
Write a one-time migration (SQL script or `@PostConstruct` runner) that pulls distinct `resourceLocation` values into the `Location` table and updates each resource's `location_id` FK.

**Acceptance:** No data loss. All existing resources point to the correct Location. Old `resource_location` column is dropped.

---

### 7. Fix resourceId mass-assignment on POST
Prevent clients from setting `resourceId` on create requests (currently allows overwriting existing resources). Either null out the ID before save or add `@JsonProperty(access = READ_ONLY)` to the field.

**Acceptance:** POST with a body containing an existing `resourceId` creates a new resource instead of overwriting.

---

### 8. Add DTOs for Resource and Location
Create DTOs to control what each endpoint sends and receives, and to break the bidirectional JSON serialization cycle cleanly:

- **`ResourceResponseDTO`** — flattens or nests the location info (e.g. `locationId` + `locationName`) so the resource endpoint doesn't dump the full Location object.
- **`ResourceCreateDTO`** / **`ResourceUpdateDTO`** — accepts a `locationName` string (not a Location object). No `resourceId` field on create, solving card #7 at the DTO level.
- **`LocationResponseDTO`** — for `GET /api/locations` (dropdown): just `locationId` + `name`.
- **`LocationDetailDTO`** — for `GET /api/locations/{id}` (detail page): includes `locationId`, `name`, and a list of `ResourceResponseDTO` for the resources at that location.

Add a mapper class or use constructor mapping to convert between entities and DTOs in the service layer.

**Acceptance:** Controllers accept/return DTOs, not entities. No `@JsonIgnore` hacks needed. Swagger docs reflect the actual request/response shapes.
