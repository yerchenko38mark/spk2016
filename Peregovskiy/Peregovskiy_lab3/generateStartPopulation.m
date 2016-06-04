function popNum = generateStartPopulation(NVARS, FitnessFcn, options)
populationSize = sum(options.PopulationSize);
popNum = cell(populationSize, 1);
for i = 1:populationSize
    popNum{i} = randperm(NVARS); 
end


