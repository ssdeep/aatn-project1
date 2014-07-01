## Graph visualization from flow files
# include libraries for diagram and shape
library('shape')
library('diagram')

# for each test case read the output files and plot graphs
for(k in 3:25)
{
  # read the file outputted from the program into a matrix
  mymat <- matrix(scan(sprintf("k%doutput.dat", k)), ncol = 40, byrow = T)
  # plot the matrix with flow values
  #png(filename=sprintf("k%d.png", k),width=4, height=4, units="in", res=300)
  plotweb(mymat, legend = F, maxarrow = 3, main=sprintf("Network topology when k = %d", k), lab.size=0.8)
  #dev.off()
}

# for the overall experimental design do the plotting
mydata <- read.csv("experimentStats.dat")
plot(mydata$k, mydata$cost, type="o", xlab="k", ylab="total cost of the network", main="Dependency of total cost on k")
plot(mydata$k, mydata$density, type="o", xlab="k", ylab="density of the network", main="Dependency of density on k")