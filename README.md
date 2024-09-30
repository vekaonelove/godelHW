# godelHW
*Test task for the Junior Java Developer position*


**WORKFLOW**
-
1) Pick technologies and stack to be used:
   - Java-17
   - Spring/Springboot
   - Lombok
   - MySQL/H2 (both versions are present)

2) Create project structure according to REST principles:
   - Controller layer
   - Service layer
   - DAO (data access object) and DTO (data transition object)

3) Specify some exact classes and their functionality acctording to the layers:
   - **Controller layer**:
       - a single class *UrlShortenerController* with functionality of providing shortened URL and retrieving the initial one from the shortened given.
   - **Service layer**:
     - class *UrlShortenerService* that allows us to transform URLs and restore them
     - contains sub-package with a single exception *UrlNotFoundException*
   - DAO (data access object) layer
       - class *UrlEntity* covered by Lombok annotations. Contains ID, initial link and shortened link fields.
       - interface *UrlShortenerRepository* extendinf JpaRepository features
   - DTO (data transition object) layer
       - classes *ShortenUrlRequest* and *ShortenUrlResponse*
         
4) Add Unit tests and logs

**PROSPECTS / TODO / Project Scalability**
- increase security by using additional encryption
- add flexible shortened URL sizes (from 4 to 8, for example - depends). At the moment it's constant and equal to 6.
