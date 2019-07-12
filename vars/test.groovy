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

    try{
      def builds = currentBuild.rawBuild.getParent().builds
      for(build in builds){
        def buildNum = build.number
        def buildBranch = build.getEnvironment().BRANCH
        if (build.getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
          build.doKill()
          println("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
          build.description = "Superseded by build #${currentBuildNum}"
        }
      }
      
    } catch(NoSuchElementException e){
      println("ex caught")
    }
    catch(Exception e){
      println("Exception caught")
    }
  }



