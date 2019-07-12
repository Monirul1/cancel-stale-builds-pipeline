def builds = currentBuild.rawBuild.getParent().builds
println("BUILDS ${builds}")
