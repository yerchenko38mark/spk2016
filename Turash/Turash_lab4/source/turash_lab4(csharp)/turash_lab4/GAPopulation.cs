using System;

namespace turash_lab4
{
    class GAPopulation
    {
        private static readonly Random randg = new Random(); // Рандомний генератор
        public int PopSize;
        public GAIndividual[] Ind;
        public int BestIndex; // індекс найкращого індивідума в масиві Ind

        public GAPopulation(int psize, int gsize, double[] minRange,
            double[] maxRange)
        {
            // створення рандомної популяції кількісью PopSize
            PopSize = psize;
            Ind = new GAIndividual[PopSize];
            Console.WriteLine("Початкова популяцiя");
            for (int i = 0; i < PopSize; i++)
            {
                Ind[i] = new GAIndividual(gsize, minRange, maxRange);
                Console.WriteLine(i + " " + Ind[i] + " ");
            }
            Evaluate();
        }

        public GAPopulation(GAIndividual[] p)
        {
            // Створення популяції з такимиж індивідумами як в p
            PopSize = p.Length;
            Ind = new GAIndividual[PopSize];
            Console.WriteLine("Нова популяцiя");
            for (int i = 0; i < PopSize; i++)
            {
                Ind[i] = p[i];
                Console.WriteLine(i + " " + Ind[i] + " ");
            }
            Evaluate();
        }

        public GAPopulation Generate(GAPopulation p, int xrate, int mrate,
            double[] minRange, double[] maxRange)
        {
            //Створення нової популяції з р, xrate відсотків індивідумів нового населення є
            //схрещування, mrate відсотків з них створюються в результаті мутації, а інші по відтворення.
            if (xrate < 0 || xrate > 100 || mrate < 0 || mrate > 100
                || xrate + mrate > 100)
                Console.WriteLine("error: xrate i/чи mrate неправильно встановленi");
            var newg = new GAIndividual[p.PopSize];


            int newg_index = 0;
            int xn = xrate*p.PopSize/100;
            //xn: Кількість нащадків, які будуть схрешення
            var mn = mrate*p.PopSize/100;
            // mn: кількість нащадків які будуть створенні мутацією
            // схрещування:
            for (var i = 0; i < xn; i++)
            {
                // select to parents for cross-over:
                var p1 = p.tr_select();
                var p2 = p.tr_select();

                newg[newg_index++] = GAIndividual.Xover1P(p.Ind[p1], p.Ind[p2]);
            }
            // мутація:
            for (var i = 0; i < mn; i++)
                newg[newg_index++] = p.Ind[p.tr_select()].Mutate(minRange,
                    maxRange);
            // відтворення:
            for (var i = newg_index; i < p.PopSize; i++)
                newg[i] = p.Ind[p.tr_select()];


            return new GAPopulation(newg);
        }

        public int tr_select()
        {
            //турнірна вибірка розміром PopSize/10
            //вона повертає індекс вибраного особи в Ind []
            var sIndex = randg.Next(PopSize);
            // індекс вибраного індивідума
            var sFitness = Ind[sIndex].Fitness;
            var trSize = Math.Min(10, PopSize);
            for (var i = 1; i < trSize; i++)
            {
                var tmp = randg.Next(PopSize);
                if (Ind[tmp].Fitness > sFitness)
                {
                    sIndex = tmp;
                    sFitness = Ind[tmp].Fitness;
                }
            }
            return sIndex;
        }

        private void Evaluate()
        {
            //оцінювання
            var best = 0;
            // індекс найкрощого індивідума
            var bestFitness = Ind[0].Fitness;
            var sum = Ind[0].Fitness;
            // сума придатності особин даної популяції
            for (int i = 1; i < PopSize; i++)
            {
                sum += Ind[i].Fitness;
                if (Ind[i].Fitness > bestFitness)
                {
                    best = i;
                    bestFitness = Ind[i].Fitness;
                }
            }
            BestIndex = best;
        }

        public string toString() => "best individual = " + Ind[BestIndex];
    }
}