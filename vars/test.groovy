def call(){

cancelStaleBuilds() 

}

  def cancelStaleBuilds() {

    currentBuildNum = currentBuild.number
    currentBranch = env.BRANCH

    try{
      def builds = currentBuild.rawBuild.getParent().builds
      builds.each{ build ->
        def buildNum = build.number
        def buildBranch = build.getEnvironment().BRANCH
        if (build.getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
          build.doKill()
          println("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
          build.description = "Superseded by build #${currentBuildNum}"
        }
      }
    } catch(Exception e){
      printn("ex caught")
    }
  }


