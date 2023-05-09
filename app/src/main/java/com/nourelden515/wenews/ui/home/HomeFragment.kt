package com.nourelden515.wenews.ui.home

import androidx.lifecycle.ViewModelProvider
import com.nourelden515.wenews.R
import com.nourelden515.wenews.base.BaseFragment
import com.nourelden515.wenews.base.ViewModelFactory
import com.nourelden515.wenews.data.NewsRepository
import com.nourelden515.wenews.databinding.FragmentHomeBinding
import com.nourelden515.wenews.model.NewsRequest

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(NewsRepository())
        )[HomeViewModel::class.java]
    }

    override fun setup() {
        showActionBarAndBottomNav()
        viewModel.predict(
            NewsRequest("A second U.S. judge on Thursday blocked President Donald Trump’s administration from enforcing new rules that undermine an Obamacare requirement for employers to provide insurance that covers women’s birth control. U.S. District Judge Haywood Gilliam Jr. in Oakland, California, said the federal government likely did not follow proper administrative procedures in promulgating the new rules, and put them on hold while a lawsuit challenging their legality proceeds. The decision followed a similar ruling from a federal judge in Philadelphia last Friday that blocked the administration from enforcing rules it announced in October allowing businesses or nonprofits to obtain exemptions on moral or religious grounds. Gilliam ruled on a lawsuit pursued by Democratic attorneys general in California, Delaware, Maryland, New York and Virginia. He said that a preliminary injunction was necessary given the “dire public health and fiscal consequences” that could result as a result of the administration adopting the rules without the input of interested parties. “If the Court ultimately finds in favor of Plaintiffs on the merits, any harm caused in the interim by rescinded contraceptive coverage would not be susceptible to remedy,” he wrote. California Attorney General Xavier Becerra said in a statement that, given last week’s decision in Pennsylvania, “today’s ruling amounts to a one-two punch against the Trump administration’s unlawful overreach.” The U.S. Justice Department defended the rules in court.  Lauren Ehrsam, a department spokeswoman, said the agency disagreed with the ruling and was evaluating its next steps. “This administration is committed to defending the religious liberty of all Americans and we look forward to doing so in court,” Ehrsam said in a statement. The lawsuit is among several that Democratic state attorneys general filed after the Republican Trump administration revealed the new rules on Oct. 6, which targeted the contraceptive mandate implemented as part of 2010’s Affordable Care Act, popularly known as Obamacare. The rules will let businesses or nonprofits lodge religious or moral objections to obtain an exemption from the law’s mandate that employers provide contraceptive coverage in health insurance with no co-payment. Conservative Christian activists and congressional Republicans praised the move, while reproductive rights advocates and Democrats criticized it")
        )

        viewModel.predictionResponse.observe(viewLifecycleOwner) {
            it.toData()?.let { it1 -> log(it1.prediction) }
        }
    }

}