[x1,x2] = meshgrid(-5:0.1:5);
Z =-(sin(x1).*sin((x1.^2)/pi) + sin(x2).*sin((2*x2.^2)/pi));
mesh(Z)