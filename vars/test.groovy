def call(){
  
  currentBuildNum = currentBuild.number
  currentBranch = env.BRANCH

  def builds = currentBuild.rawBuild.getParent().builds
  
  def list = []
  list.add(builds)
  
  list.each{ i ->
    
    def buildNum = i.environment.get("BUILD_NUMBER")
//    def buildBranch = i.getEnvironment().BRANCH
    
    println "PRINTTTTT ${i}"
}

  
  
}
