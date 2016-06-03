function newChildren = lopsidedMutation(parents, options, GenomeLength, ...
FitnessFcn, state, thisScore, thisPopulation, mutationRate)
newChildren = zeros(length(parents),GenomeLength);
    for i=1:length(parents)
        currChild = thisPopulation(parents(i),:);
        mutationPoints = find(rand(1,length(currChild)));
        currChild(mutationPoints) = ~currChild(mutationPoints);
        newChildren(i,:) = currChild;
    end  
end