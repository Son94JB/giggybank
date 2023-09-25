package com.d208.giggy.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentCommunityPostRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityPostRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "CommunityPostRegisterFr giggy"
@AndroidEntryPoint
class CommunityPostRegisterFragment : BaseFragment<FragmentCommunityPostRegisterBinding>(FragmentCommunityPostRegisterBinding::bind, R.layout.fragment_community_post_register) {

    private var tempUri : Uri? = null
    private val STORAGE_PERMISSION_CODE = 1
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                Log.d(TAG, "URI-JoIN: $uri")
                // 선택한 이미지 URI를 사용하여 이미지뷰에 설정합니다.
                binding.fragmentCommunityPostRegisterImageView.visibility = View.VISIBLE
                binding.fragmentCommunityPostRegisterImageView.setImageURI(uri)
                tempUri = uri
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()


    }

    fun init() = with(binding) {
        fragmentCommunityPostRegisterImageView.setOnClickListener{
            checkAndRequestStoragePermission()
        }
        fragmentCommunityPostRegisterPostButton.setOnClickListener {
            if(fragmentCommunityPostRegisterTitleEditView.text.toString().isNullOrEmpty()){
                showSnackbar("제목을 입력해주세요")
            }
            else if (fragmentCommunityPostRegisterContentEditView.text.toString().isNullOrEmpty()){
                showSnackbar("내용을 입력해주세요")
            }
            else if(fragmentCommunityPostRegisterChipGroup.checkedChipId == -1){
                showSnackbar("카테고리를 선택해주세요")
            }
            else{
                showSnackbar("${fragmentCommunityPostRegisterPostTabLayout.selectedTabPosition}")
                // 게시글 등록
                if(tempUri != null){
                    val imageMultiPart = createMultipartFromUri(requireContext(), tempUri!!)
                }

            }
        }
    }
    private fun checkAndRequestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 이미 퍼미션을 가지고 있는 경우
            openGalleryForImage()
        } else {
            // 퍼미션을 요청합니다.
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        // 이미지를 선택하는 요청을 `ActivityResultLauncher`로 보냅니다.
        imagePickerLauncher.launch(intent)
    }

    private fun createMultipartFromUri(context: Context, uri: Uri): MultipartBody.Part? {
        val file: File = getFileFromUri(context, uri) ?: return null
        // 파일을 가져오지 못한 경우 처리할 로직
        val requestFile: RequestBody = createRequestBodyFromFile(file)
        Log.d(TAG, "createMultipartFromUri: ${file.name}")
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }
    private fun getFileFromUri(context: Context, uri: Uri): File? {
        val filePath = uriToFilePath(context, uri)
        return if (filePath != null) File(filePath) else null
    }

    private fun uriToFilePath(context: Context, uri: Uri): String? {
        Log.d(TAG, "URI-join:$uri")
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val filePath = cursor?.getString(columnIndex!!)
        cursor?.close()
        return filePath
    }

    private fun createRequestBodyFromFile(file: File): RequestBody {
        val MEDIA_TYPE_IMAGE = "image/png".toMediaTypeOrNull()
        val inputStream: InputStream = FileInputStream(file)
        val byteArray = inputStream.readBytes()
        return RequestBody.create(MEDIA_TYPE_IMAGE, byteArray)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 퍼미션이 승인된 경우
                openGalleryForImage()
            } else {
                // 퍼미션이 거부된 경우
                showSnackbar("갤러리 접근 권한이 필요합니다.")
            }
        }
    }




}