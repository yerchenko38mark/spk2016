
[X1, X2] = meshgrid(-5.12:.01:5.12); 
             Z =abs(mod(X1,2))+abs(mod(X2,2));
             mesh(Z)
