using System;

namespace turash_lab4
{
    class GAEvolve
    {
        public double[] BestFitness;//є найкращим значенням для і-го покоління даної еволюції
        public GAIndividual BestInd; //найкращий індивідум(х) даної еволюції
        public GAEvolve(int generations, int popSize, int genomeSize, int xrate, int mrate, double[] minRange, double[] maxRange)
        {

            BestFitness = new double[generations];
            var gap = new GAPopulation(popSize, genomeSize, minRange, maxRange);
            BestFitness[0] = gap.Ind[gap.BestIndex].Fitness;

            for (var i = 1; i < generations; i++)
            {
                gap = gap.Generate(gap, xrate, mrate, minRange, maxRange);
                BestFitness[i] = gap.Ind[gap.BestIndex].Fitness;

                Console.WriteLine("Найкраще значення поколiння " + BestFitness[i]);
            }
            BestInd = gap.Ind[gap.BestIndex];
        }

        static void Main(string[] args)
        {

            double[] min = { -10f };
            double[] max = { 53 };
            var gae = new GAEvolve(100, 5, 1, 70, 20, min, max);
            Console.WriteLine("Результат:");
            Console.WriteLine(gae.BestInd);
            Console.ReadKey();

        }
    }
}