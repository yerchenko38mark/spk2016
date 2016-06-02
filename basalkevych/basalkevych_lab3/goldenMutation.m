function children = goldenMutation(parents, options, NVARS, ...
    FitnessFcn, state, thisScore, thisPopulation, mutationRate)
children = cell(length(parents), 1);
for i=1:length(parents)
    parent = thisPopulation{parents(i)}; 
   XorPoint = floor(0.61803*length(parent)) 
if (XorPoint == 0)
      XorPoint=XorPoint + 1;
end
    child = parent;
    child(XorPoint) = parent(XorPoint+1);
    child(XorPoint+1) = parent(XorPoint);
    children{i} = child; 
end
