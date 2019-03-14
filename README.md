[![CircleCI](https://circleci.com/gh/samzhu/nexus-help.svg?style=svg)](https://circleci.com/gh/samzhu/nexus-help)

# nexus-help
delete nuxus docker images

```
spring:
# set your mail
  mail:
    host: "smtp.office365.com"
    username: "notice@outlook.com.tw"
    password: "123456"
    properties:
      mail:
        smtp:
          auth: true
          starttls.enable: true
          socketFactory:
            port: 578
            class: javax.net.ssl.SSLSocketFactory

logging:
  level:
    root: info

management:
  endpoints:
    web:
      exposure:
        include: "*"

# mail info
notify:
  mail:
    from: "notice@outlook.com.tw"
    to: "admin001@notice.com.tw,admin002@outlook.com.tw"
    subject: "nexus-help schedule"

# cron job
job-clean-nexus:
  cron: "0 0 21 * * 5"

# use rest api to delete docker images
nexus:
  url: "https://nexus.domain.com"
  username: "admin"
  password: "password"
  repository: "repositoryName"
  format: "docker"
  cleanup-policies:
  # Exclude Image Name
  - policies-name: policiesRegularExpressionExclude
    field: name
    regex: "csnt/pinpoint-agent"
  # Exclude Image RELEASE Version
  - policies-name: policiesRegularExpressionExclude
    field: version
    regex: "[0-9]*[.][0-9]*[.][0-9]*[.]RELEASE"
  - policies-name: policiesRegularExpressionExclude
    field: version
    regex: "[0-9]*[.][0-9]*[.][0-9]*[.]RELEASE.DEV"
  - policies-name: policiesRegularExpressionExclude
    field: version
    regex: "[0-9]*[.][0-9]*[.][0-9]*[.]RELEASE.TEST"
  - policies-name: policiesRegularExpressionExclude
    field: version
    regex: "[0-9]*[.][0-9]*[.][0-9]*[.]RELEASE.UAT"
  - policies-name: policiesRegularExpressionExclude
    field: version
    regex: "[0-9]*[.][0-9]*[.][0-9]*[.]RELEASE.PROD"

# After Exclude, Other Images Delete
```
