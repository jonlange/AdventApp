package com.livexlive.adventapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.livexlive.adventapp.databinding.FragmentDay2Binding
import java.io.InputStream

/**
 * https://adventofcode.com/2021/day/2
 */
class Day2Fragment : Fragment() {

    private var _binding: FragmentDay2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDay2Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_Day2Fragment_to_Day3Fragment)
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

    // Calculate number of increases in final position after commands in commands.txt
    private fun doProblem1() {
        val inputStream: InputStream = resources.openRawResource(R.raw.commands)

        var currentDepth = 0
        var currentHorizontal = 0

        inputStream.bufferedReader().forEachLine {
            val directionPair = it.split(" ")

            when(directionPair[0]) {
                "forward" -> {
                    currentHorizontal += directionPair[1].toInt()
                }
                "down" -> {
                    currentDepth += directionPair[1].toInt()
                }
                "up" -> {
                    currentDepth -= directionPair[1].toInt()
                }
            }

        }

        binding.textviewSolution.text = (currentDepth * currentHorizontal).toString()
        binding.textviewSolution.visibility = View.VISIBLE
    }

    // Calculate number of increases in groups of 3
    private fun doProblem2() {
        val inputStream: InputStream = resources.openRawResource(R.raw.commands)

        var currentDepth = 0
        var currentHorizontal = 0
        var currentAim = 0

        inputStream.bufferedReader().forEachLine {
            val directionPair = it.split(" ")

            when(directionPair[0]) {
                "forward" -> {
                    currentHorizontal += directionPair[1].toInt()
                    currentDepth += directionPair[1].toInt() * currentAim
                }
                "down" -> {
                    currentAim += directionPair[1].toInt()
                }
                "up" -> {
                    currentAim -= directionPair[1].toInt()
                }
            }

        }

        binding.textviewSolution.text = (currentDepth * currentHorizontal).toString()
        binding.textviewSolution.visibility = View.VISIBLE
    }

}