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
        currentBranch = env.BRANCH
        try {
          def list = []
          list.add(currentBuild.rawBuild.getParent().builds)

          for(i = 0; i < list.size(); i++)
          {
            def buildNum = i.number
            def buildBranch = i.getEnvironment().BRANCH

            if (i.getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
                i.doKill()
                log("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
                i.description = "Superseded by build #${currentBuildNum}"
            }

          }
        } catch (NoSuchElementException ex) {
            log('[cancelStaleBuilds] Caught NoSuchElementException. No action needed.')
        } catch (Exception e) {
            log("[cancelStaleBuilds] Caught exception: ${e}")
        }
}

def log(String string){
  println("* [Jenkinsfile] ${string}")
}
