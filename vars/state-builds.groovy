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
                    println("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
                    build.description = "Superseded by build #${currentBuildNum}"
                }
            }
        } catch (NoSuchElementException ex) {
            println('[cancelStaleBuilds] Caught NoSuchElementException. No action needed.')
        } catch (Exception e) {
            println("[cancelStaleBuilds] Caught exception: ${e}")
        }
    }
}
cancelStaleBuilds()
buildSource()
