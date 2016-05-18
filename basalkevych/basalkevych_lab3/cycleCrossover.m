function xoverKids  = cycleCrossover(parents, options, NVARS, ...
    FitnessFcn, thisScore, thisPopulation)
sz = size(thisPopulation);
parentsSize = sz(2);
firstIndex = randi([1 parentsSize], 1, 1)
secondIndex = randi([1 parentsSize], 1, 1)
while (firstIndex == secondIndex)
    secondIndex = randi([1 parentsSize], 1, 1)
end

parent1 = thisPopulation(firstIndex)
parent2 = thisPopulation(secondIndex)

copyParent1 = parent1;
startPos = randi([1 parentsSize], 1, 1);
cycle = []
p = 1

while(copyParent1(startPos) ~= 0)
   cycle(p) = startPos;
   p = p + 1
   curr = copyParent1(startPos)
   copyParent1(startPos) = 0;
   startPos = copyParent2(curr);
end

child1 = parent1;
child2 = parent2;

for i = 1:p-1
    temp = child1(cycle(i));
    child1(cycle(i)) = child2(cycle(i));
    child2(cycle(i)) = temp;
end

xoverKids{firstIndex} = child1;
xoverKids{secondIndex} = child2;
end
