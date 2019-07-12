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
      println ("Build ${buildNum} and ${buildBranch}") 
    
    }
    
  } catch(Exception e){
    println("Exception caught")
  }
}

