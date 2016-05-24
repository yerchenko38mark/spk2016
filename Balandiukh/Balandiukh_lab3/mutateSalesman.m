function mutationChildren = mutateSalesman(parents ,options,NVARS, ...
    FitnessFcn, state, thisScore,thisPopulation,mutationRate)
%   Custom mutation function for traveling salesman.

% Here we swap two elements of the permutation
mutationChildren = cell(length(parents),1);% Normally zeros(length(parents),NVARS);
numberOfGenes=length(thisPopulation{parents(1)});
for i=1:length(parents)
    parent = thisPopulation{parents(i)}; % Normally thisPopulation(parents(i),:)
%Define random point
   XorPoint=mod(ceil(rand(1)*10),numberOfGenes );
if XorPoint==0
      XorPoint=XorPoint+1;
end
    child = parent;
    child(XorPoint) = parent(XorPoint+1);
    child(XorPoint+1) = parent(XorPoint);
    mutationChildren{i} = child; % Normally mutationChildren(i,:)
end
