def call(){
  
def list = []
          list.add(currentBuild.rawBuild.getParent().builds)

          list.each{ i ->
            println "PRINT ${i}"
            
          }
  
  
}
