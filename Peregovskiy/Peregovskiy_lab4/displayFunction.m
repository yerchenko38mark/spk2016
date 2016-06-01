clc

a=12;
b=-8;
c=-40;
d=3;

x=-10:1:53;
y=a+b*x+c*x.^2+d*x.^3;
plot(x,y)
grid on

minX=fminbnd(@(x) a+b*x+c*x^2+d*x^3,-10,53)
minY=a+b*minX+c*minX^2+d*minX^3

maxX=fminbnd(@(x) -(a+b*x+c*x^2+d*x^3),-10,53)
maxY=a+b*maxX+c*maxX^2+d*maxX^3