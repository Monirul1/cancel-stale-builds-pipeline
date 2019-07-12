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
    for(i = 0; i < builds.size(); i++){
     def buildNum = builds[i].number
     def buildBranch =  builds[i].getEnvironment().BRANCH
      
        if (builds[i].getResult().equals(null) && currentBuildNum > buildNum && currentBranch == buildBranch) {
        builds[i].doKill()
        println("[cancelStaleBuilds] Build Cancelled: #${buildNum} ${buildBranch}")
        builds[i].description = "Superseded by build #${currentBuildNum}"
      }
    
    }
    
  } catch (NoSuchElementException ex) {
        println('[cancelStaleBuilds] Caught NoSuchElementException. No action needed.')
      } 
    catch (Exception e) {
           println("[cancelStaleBuilds] Caught exception: ${e}")
        }
}


