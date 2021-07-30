package jp.co.hivelocity.hudidentity.app.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jp.co.hivelocity.hudidentity.R
import jp.co.hivelocity.hudidentity.app.HubIdentityProvider

class WebViewActivity : AppCompatActivity() {
    private var userAgentString: String? = ""
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var llError: LinearLayout
    private lateinit var txtError: TextView
    private var url: String = ""

    companion object {
        private const val EXTRA_URL = "extra.url"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        llError = findViewById(R.id.llError)
        txtError = findViewById(R.id.txtError)
        init()
    }

    private fun init() {
        extractExtras()

        if (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        webView.scrollBarStyle = WebView.SCROLLBARS_INSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.requestFocus(View.FOCUS_DOWN)
        webView.webViewClient = CustomWebViewClient()
        val settings = webView.settings
        settings.userAgentString = "Android"//userAgentString
        settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    private fun extractExtras() {
        url = intent?.getStringExtra(EXTRA_URL) ?: ""
    }

    inner class CustomWebViewClient : WebViewClient() {

        init {
            llError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            webView.visibility = View.VISIBLE
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            progressBar.visibility = View.GONE
            txtError.text = error?.description ?: "URL Load Error"
            llError.visibility = View.VISIBLE
        }

        override fun onPageStarted(view: WebView, url: String, facIcon: Bitmap?) {
            webView.visibility = View.GONE
        }

        override fun onPageFinished(view: WebView, url: String) {
            progressBar.visibility = View.GONE
            val isShowingError = llError.visibility == View.VISIBLE
            if (isShowingError) {
                webView.visibility = View.GONE
            } else {
                webView.visibility = View.VISIBLE
            }
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Log.d("URL", url ?: "")
            Log.d(
                "RedirectUrl",
                "${HubIdentityProvider.getScheme()}://${HubIdentityProvider.getDomain()}"
            )
            if (url?.startsWith("${HubIdentityProvider.getScheme()}://${HubIdentityProvider.getDomain()}") == true) {
                /*val intent = Intent()
                intent.data = Uri.parse(url)
                setResult(RESULT_OK, intent)
                finish()*/

                val intent = Intent(Intent.ACTION_VIEW);
                intent.data = Uri.parse(url);
                startActivity(intent);
                finish()
                return true
            }
            return false
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}