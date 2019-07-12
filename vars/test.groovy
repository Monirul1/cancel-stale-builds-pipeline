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
    //builds.each{ build ->
    for(int build = 0; build < builds.size(); build++){
      def buildNum = builds[build].number
      def buildBranch =  builds[build].getEnvironment().BRANCH
      if (build.getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
        build.doKill()
        println("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
        build.description = "Superseded by build #${currentBuildNum}"
      }
    }
    
  } catch(Exception e){
    println("Exception caught")
  }
}



