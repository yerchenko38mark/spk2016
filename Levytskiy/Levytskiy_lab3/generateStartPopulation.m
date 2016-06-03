function populations = generateStartPopulation(NVARS, FitnessFcn, options)
populationSize = sum(options.PopulationSize);
populations = cell(populationSize, 1);
for i = 1populationSize
    populations{i} = randperm(NVARS); 
end
