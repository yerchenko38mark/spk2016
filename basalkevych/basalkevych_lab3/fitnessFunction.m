function scores = fitnessFunction(x, distances)
scores = zeros(size(x, 1), 1);
for j = 1:size(x, 1)
    points = x{j}; 
    sum = distances(points(end), points(1));
    for i = 2:length(points)
        sum = sum + distances(points(i-1), points(i));
    end
    scores(j) = sum;
end
end
