This repository represents a sixth of a full-sized vacation booking application, which was built as separate repositories for separate functional areas. It mainly acts as a data storage and retrieval microservice for the larger application.

This is the reviewing functional area's microservice application, which hosts API endpoints for retrieving review data for rental locations. Other functional areas can call upon these endpoints to:
1. get all reviews for a given rental property listing,
2. get all reviews for a user, or
3. add a review to the H2 repository.

An example of such an API call can be found in the Reviewing Functional Area's main repository, at: https://github.com/eblevin3811/SWE443-Group1-ReviewingFunctionalArea-MVC
