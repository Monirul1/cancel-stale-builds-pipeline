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
              def builds = currentBuild.rawBuild.getParent().builds
              for (i = 0; i < builds.size(); i++) {
                  def buildNum = builds[i].number
                  def buildBranch = builds[i].getEnvironment().BRANCH

                  if (builds[i].isInProgress() && currentBuildNum > buildNum && currentBranch == buildBranch) {
                      builds[i].doStop()
                      log("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
                      builds[i].description = "Superseded by build #${currentBuildNum}"
                  }
              }
          } catch (Exception e) {
              log("[cancelStaleBuilds] Caught exception: ${e}")
          }
      }
  }

  def log(String string) {
      println("* [Jenkinsfile] ${string}")
  }

