pipeline {
  agent any
  stages {
    stage('assembleDebug') {
      steps {
        sh './gradlew assembleDebug --stacktrace'
      }
    }
  }
  environment {
    GOOGLE_SERVICE = 'ewogICJwcm9qZWN0X2luZm8iOiB7CiAgICAicHJvamVjdF9udW1iZXIiOiAiMTAx NDM4MDg5NTYzNyIsCiAgICAiZmlyZWJhc2VfdXJsIjogImh0dHBzOi8vbGliZm1k ZW1vLmZpcmViYXNlaW8uY29tIiwKICAgICJwcm9qZWN0X2lkIjogImxpYmZtZGVt byIsCiAgICAic3RvcmFnZV9idWNrZXQiOiAibGliZm1kZW1vLmFwcHNwb3QuY29t IgogIH0sCiAgImNsaWVudCI6IFsKICAgIHsKICAgICAgImNsaWVudF9pbmZvIjog ewogICAgICAgICJtb2JpbGVzZGtfYXBwX2lkIjogIjE6MTAxNDM4MDg5NTYzNzph bmRyb2lkOjA1ZDkyOTM3YTg2YmQzZTMiLAogICAgICAgICJhbmRyb2lkX2NsaWVu dF9pbmZvIjogewogICAgICAgICAgInBhY2thZ2VfbmFtZSI6ICJjb20udGlzdG9y eS5mcmVlbW1lci5saWIubGliZm0uZGVtbyIKICAgICAgICB9CiAgICAgIH0sCiAg ICAgICJvYXV0aF9jbGllbnQiOiBbCiAgICAgICAgewogICAgICAgICAgImNsaWVu dF9pZCI6ICIxMDE0MzgwODk1NjM3LXU2MjcyNzRkcDFzdjk5bWxuZzl1dW8wZXIw MWRrZHNmLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwKICAgICAgICAgICJj bGllbnRfdHlwZSI6IDMKICAgICAgICB9CiAgICAgIF0sCiAgICAgICJhcGlfa2V5 IjogWwogICAgICAgIHsKICAgICAgICAgICJjdXJyZW50X2tleSI6ICJBSXphU3lB WnVwaUI1Z0pUdDVCbVJ2dTNXSXo3Ym05NTRpMFlHME0iCiAgICAgICAgfQogICAg ICBdLAogICAgICAic2VydmljZXMiOiB7CiAgICAgICAgImFwcGludml0ZV9zZXJ2 aWNlIjogewogICAgICAgICAgIm90aGVyX3BsYXRmb3JtX29hdXRoX2NsaWVudCI6 IFsKICAgICAgICAgICAgewogICAgICAgICAgICAgICJjbGllbnRfaWQiOiAiMTAx NDM4MDg5NTYzNy11NjI3Mjc0ZHAxc3Y5OW1sbmc5dXVvMGVyMDFka2RzZi5hcHBz Lmdvb2dsZXVzZXJjb250ZW50LmNvbSIsCiAgICAgICAgICAgICAgImNsaWVudF90 eXBlIjogMwogICAgICAgICAgICB9CiAgICAgICAgICBdCiAgICAgICAgfQogICAg ICB9CiAgICB9CiAgXSwKICAiY29uZmlndXJhdGlvbl92ZXJzaW9uIjogIjEiCn0='
  }
}