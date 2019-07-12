def call(){
cancelStaleBuilds()
buildSource()

}

def buildSource(){
  for(i = 0; i < 200000; i++)
  {
    print "."
  }

}
def cancelStaleBuilds() {
        currentBuildNum = currentBuild.number
        currentBranch = env.getEnvironment()
  log("PRINT ${currentBranch}")
     
}

def log(String string){
  println("* [Jenkinsfile] ${string}")
}
