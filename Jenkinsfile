node() {
stage('codecheckout')
checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHubCredentials', url: 'https://github.com/NagireddyGuduru/myweb.git']]])

def mvnHome = tool 'MAVEN_HOME'
stage('compile')
sh "${mvnHome}/bin/mvn clean compile" 

stage('package')
sh "${mvnHome}/bin/mvn clean package" 

stage('sonar')
sh "'${mvnHome}/bin/mvn' -Dsonar.host.url=http://34.216.53.62:9000 clean sonar:sonar" 

stage('nexus')
sh "${mvnHome}/bin/mvn clean deploy" 

stage('deploy')
echo 'Deloy Step yet to configure'
}
