# Simple j2ee from combination of EJB Rest, JBOSS JBPM and Push message (AI included)

### Features
* All message between users are stored im MongoDB and the topic under discussion is idetified by AI 
* We can add new process and start any added process from admin panel 
* All user behaviors are stored in MongoDB.


### Config:
> *This project have three parts that must be on three different server but these config are on one serve*

* Main project on TomEE `/localhost:8080` , JMX PORT: `1101` or `1102` or ...
* Push Message chat Server on Tomcat `/localhost:80` , JMX PORT: `1100`
* Process Engine Server with RMI , PORT: `1099`

#### NOTICE.
> * All logs store in MongoDB `localhost:27017`  and before run project MUST be online.
> * All OracleDB command and TomEE config for JavaStandardSecurity is in `/Config` directory. 
> * In first ProcessEngineServer run, uncomment Create-Drop for Hibernate to create DBschema.
> * Dataset for AI is in `/Webpush+ai` directory and must be copeid in Desktop.
