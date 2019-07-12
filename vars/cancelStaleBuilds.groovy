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
    stage('Cancel Stale Builds') {
        currentBuildNum = currentBuild.number
        currentBranch = env.BRANCH
        try {
            currentBuild.rawBuild.getParent().builds.each {build ->
                def buildNum = build.number
                def buildBranch = build.getEnvironment().BRANCH
                if (build.getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
                    build.doKill()
                    log("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
                    build.description = "Superseded by build #${currentBuildNum}"
                }
            }
        } //catch (NoSuchElementException ex) {
            //log('[cancelStaleBuilds] Caught NoSuchElementException. No action needed.')
  //      } 
  catch (Exception e) {
            log("[cancelStaleBuilds] Caught exception: ${e}")
        }
    }
}

def log(String string){
  println("* [Jenkinsfile] ${string}")
}
