/**
* This file contains jobs for wss pipeline.
* In order to create pipeline for a new branch, add it to branches array
*/
def gitrepo = 'https://github.com/NagireddyGuduru/client-demo-project.git'
def branches = ['origin/aws_branch', 'origin/master', 'origin/demo_branch']

def getBuildName(def branchName, def jobName) {
    def buildNameSuffix = '${BUILD_NUMBER} (${ENV,var=\"VERSION\"})'
    return "myweb-${branchName}-${jobName}-${buildNameSuffix}"
}

/**
* Start of the loop to create pipeline for each branch
*/
branches.each { branchName ->
    //def shortName = branchName.replaceAll('origin/','').replaceAll('elopment','').replace('/', '-')
   // def tag_name = 'build/${VERSION/%\\0/$BUILD_NUMBER}'
    def build_number = '$BUILD_NUMBER'
    
   
    freeStyleJob("myweb-${build_number}-qa") {
       // label("win2008r2-wss")
        logRotator(-1,5)
        throttleConcurrentBuilds {
            categories(['fit'])
        }
        wrappers {
            buildName(getBuildName(build_number, "qa"))
        }
        steps {
           // shell("rm -rf *")
            environmentVariables {
                propertiesFile("env_vars.properties")
            }
          }
   }
}
