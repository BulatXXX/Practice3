package com.example.practice3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.practice3.databinding.FragmentCameraBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private var cameraExecutor: ExecutorService? = null


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCameraBinding.inflate(layoutInflater , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        startCamera()

        binding.makeNoteBtn.setOnClickListener {
            //SaveData
            requireActivity().getExternalFilesDir(null)
                ?.let { it1 -> DateSaver.saveCurrentDateTimeToFile(it1) }

        }
        binding.toRvFragment.setOnClickListener {
            val action = CameraFragmentDirections.actionCameraFragmentToRecyclerFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.shutdown()
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val cameraProvider = cameraProviderFuture.get()
        cameraProviderFuture.addListener(Runnable {


            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this , cameraSelector , preview)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } , ContextCompat.getMainExecutor(requireContext()))
    }


}


