function state = plotFunction(options, state, flag, points)
[unused, i] = min(state.Score);
genotype = state.Population{i};
plot(points(:, 1), points(:, 2), 'bo');
hold on;
plot(points(genotype, 1), points(genotype, 2));
hold off
end

