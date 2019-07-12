def call(){
  
  currentBuildNum = currentBuild.number
  currentBranch = env.BRANCH

  def builds = currentBuild.rawBuild.getParent().builds
  
  def list = []
  list.add(builds)
  
  list.each{ i ->
    
    println "PRINTTTTT ${i}"
}

  
  
}
