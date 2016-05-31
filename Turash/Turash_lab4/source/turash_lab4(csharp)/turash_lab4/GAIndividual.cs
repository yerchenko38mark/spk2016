using System;

namespace turash_lab4
{
    class GAIndividual
    {
        private static Random randg = new Random(); // Рандомний генератор
        public int GenomeSize;
        public double[] Genome;
        public double Fitness;

        public GAIndividual(int gsize, double[] minRange, double[] maxRange)
        {
            // створити випадкового індивідума довжиною gsize
            // і-й ген повине бути в діапазоні між minRange[i] і maxRange[i]
            GenomeSize = gsize;
            Genome = new double[GenomeSize];
            for (var i = 0; i < GenomeSize; i++)
            {
                Genome[i] = (double)randg.NextDouble() * (maxRange[i] - minRange[i]) + minRange[i];
            }


            EvalFitness();// оцінити придатність цього нового індивідума
        }

        public GAIndividual(double[] d)
        {
            // Створити індивідума, що його ген є таки же як d []

            GenomeSize = d.Length;
            Genome = new double[GenomeSize];
            for (var i = 0; i < GenomeSize; i++)
            {
                Genome[i] = d[i];
            }
            EvalFitness();// оцінити придатність цієї нового індивідума
        }

        public GAIndividual Mutate(double[] minRange, double[] maxRange)
        {
            // rate це шанс кожного гена мутувати
            var rate = 1.0f / (double)GenomeSize;
            var result = new double[GenomeSize];
            for (var i = 0; i < GenomeSize; i++)
                result[i] = Genome[i];
            // застосування точкової мутації
            for (var i = 0; i < GenomeSize; i++)
                result[i] = randg.NextDouble() * (maxRange[i] - minRange[i]) + minRange[i];

            return new GAIndividual(result);
        }

        public static GAIndividual Xover1P(GAIndividual f, GAIndividual m)
        {
            // одноточковий кросинговер
            var rng = new Random();
            int xpoint = 1 + rng.Next(1);
            double[] child = new double[f.GenomeSize];
            for (var i = 0; i < xpoint; i++)
            {
                child[i] = f.Genome[i];
            }
            for (int i = xpoint; i < f.GenomeSize; i++)
            {
                child[i] = m.Genome[i];
            }
            return new GAIndividual(child);
        }

        public override string ToString()
        {
            var s = "[";
            s += Math.Round(Genome[0], 5) + "]";
            s += " Fitness = " + Math.Round(Fitness, 5);
            return s;
        }

        private void EvalFitness() => Fitness = -(62 - Genome[0] - 86 * Genome[0] * Genome[0] + 2 * Genome[0] * Genome[0] * Genome[0]);
    }
}