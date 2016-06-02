numberOfPoints = 10;
points = zeros(numberOfPoints, 2);
mas = zeros(numberOfPoints, 2);
x = cell(1, 1);
 for n = 1: numberOfPoints
     points(n,1) = randi([0 10], 1, 1);
     mas(1,n) = points(n, 1);
     points(n,2) = randi([0 10], 1, 1);
 end
x{1} = mas(1, :);
distances = zeros(numberOfPoints);
for i=1:numberOfPoints,
    for j=1:i,
        x1 = points(i, 1);
        y1 = points(i, 2);
        x2 = points(j, 1);
        y2 = points(j, 2);
        distances(i, j) = sqrt((x1-x2)^2+(y1-y2)^2);
        distances(j, i) = distances(i, j);
    end;
end;
 
FitnessFcn = @(x) fitnessFunction(x, distances);
show_plot = @(options, state, flag) plotFunction(options, ...
    state, flag, points);

beginTime = clock;

options = gaoptimset('CreationFcn', @generateStartPopulation, ...
    'CrossoverFcn', @crossover_pmx, ...
    'MutationFcn', @goldenMutation, ...
    'PlotFcn', show_plot, ...
    'Generations', 500, 'PopulationSize', 10);
 
[x, fval, reason, output] = ga(FitnessFcn, numberOfPoints, options)

endTime = clock;
time = endTime - beginTime
