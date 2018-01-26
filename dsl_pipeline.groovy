folder('PIPELINE_JOB/CODEADDAGURU') {
        description('hpsim project foloder created')
}
freeStyleJob('PIPELINE_JOB/CODEADDAGURU/compile') {
    logRotator(-1, 10)
    scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    steps {
        maven('clean compile')
    }
    publishers {
        downstream('PIPELINE_JOB/CODEADDAGURU/test', 'SUCCESS')
    }
}
mavenJob('PIPELINE_JOB/CODEADDAGURU/test') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean test')
   
   publishers {
        downstream('PIPELINE_JOB/CODEADDAGURU/sonar', 'SUCCESS')
    }
}
mavenJob('PIPELINE_JOB/CODEADDAGURU/sonar') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean sonar:sonar')
  publishers {
        downstream('PIPELINE_JOB/CODEADDAGURU/nexus', 'SUCCESS')
    }
}
mavenJob('PIPELINE_JOB/CODEADDAGURU/nexus') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean deploy')
}
buildPipelineView('PIPELINE_JOB/CODEADDAGURU/build-pipeline') {
    filterBuildQueue()
    filterExecutors()
    
    displayedBuilds(5)
    selectedJob('PIPELINE_JOB/CODEADDAGURU/compile')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
}
