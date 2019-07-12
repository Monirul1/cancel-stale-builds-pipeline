def call(){
  
  currentBuildNum = currentBuild.number
  currentBranch = env.BRANCH

  def builds = currentBuild.rawBuild.getParent().builds
  
  def list = []
  list.add(builds)
  
  for(i = 0; i < list.size(); i++){
  println "PRINTTTTT ${list}"
}

  
  
}
