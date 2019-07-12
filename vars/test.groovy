def call(){
  
def list = []
          list.add(currentBuild.rawBuild.getParent().builds)

          list.each{ i ->
            println "PRINT ${i}"
            
              def buildNum = i.getEnvironment().BUILD_NUMBER
              def buildBranch = i.getEnvironment().BRANCH
             println "PRINT ${buildNum} and ${buildBranch} "
          }
  
  
}
