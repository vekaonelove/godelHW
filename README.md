# godelHW
*Test task for the Junior Java Developer position*


**WORKFLOW**
-
1) Pick technologies and stack to be used:
   - Java-17
   - Spring/Springboot
   - Lombok
   - MySQL/H2 (both versions present)
   - log4j2/slf4j

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
   - **DAO (data access object) layer**:
       - class *UrlEntity* covered by Lombok annotations. Contains ID, initial link and shortened link fields.
       - interface *UrlShortenerRepository* extendinf JpaRepository features
   - D**TO (data transition object) layer**:
       - classes *ShortenUrlRequest* and *ShortenUrlResponse*
         
4) Add Unit tests for Service methods.

**PROSPECTS / TODO / Project Scalability**
- add application logs using slf4j
- increase security by using additional encryption
- add flexible shortened URL sizes (from 4 to 8, for example - depends). At the moment it's constant and equal to 6.
