package com.livexlive.adventapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.livexlive.adventapp.databinding.FragmentDay1Binding
import java.io.InputStream

/**
 * https://adventofcode.com/2021/day/1
 */
class Day1Fragment : Fragment() {

    private var _binding: FragmentDay1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDay1Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_Day1Fragment_to_Day2Fragment)
        }

        binding.buttonFirstProblem.setOnClickListener {
            doProblem1()
        }

        binding.buttonSecondProblem.setOnClickListener {
            doProblem2()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Calculate number of increases in depth.txt
    private fun doProblem1() {
        val inputStream: InputStream = resources.openRawResource(R.raw.depth)
        var lastDepth: Int? = null

        var increasedCount = 0

        inputStream.bufferedReader().forEachLine {
            val currentDepth = it.toInt()

            lastDepth?.let {
                if(currentDepth > it) {
                    increasedCount++
                }
            }

            lastDepth = currentDepth
        }

        binding.textviewSolution.text = increasedCount.toString()
        binding.textviewSolution.visibility = View.VISIBLE
    }

    // Calculate number of increases in groups of 3
    private fun doProblem2() {
        val inputStream: InputStream = resources.openRawResource(R.raw.depth)
        val depthList = mutableListOf<Int>()
        inputStream.bufferedReader().forEachLine {
            depthList.add(it.toInt())
        }

        var lastDepth: Int? = null

        var increasedCount = 0

        for(i in 0..depthList.size-3) {

            val currentDepth = depthList[i] + depthList[i+1] + depthList[i+2]

            lastDepth?.let {
                if(currentDepth > it) {
                    increasedCount++
                }
            }

            lastDepth = currentDepth
        }

        binding.textviewSolution.text = increasedCount.toString()
        binding.textviewSolution.visibility = View.VISIBLE
    }

}