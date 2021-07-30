package jp.co.hivelocity.hudidentity.app.ui.provider

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jp.co.hivelocity.hudidentity.DataSourceInjection
import jp.co.hivelocity.hudidentity.R
import jp.co.hivelocity.hudidentity.app.HubIdentityProvider
import jp.co.hivelocity.hudidentity.app.ui.WebViewActivity
import jp.co.hivelocity.hudidentity.model.provider.Provider
import jp.co.hivelocity.hudidentity.model.provider.Providers


class ProviderActivity : AppCompatActivity() {

    private lateinit var buttonLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var reloadLayout: LinearLayout
    private lateinit var btnReload: ImageView
    private lateinit var viewModel: ProviderViewModel
    private var intentLaunched = false

    companion object {
        const val EXTRA_START_MAIN = "extra.start.main"
        const val EXTRA_INTENT_LAUNCHED = "hubidentity.extra.intent.launch"

        fun startHubIdentityMain(activity: Activity) {
            val intent = Intent(activity, ProviderActivity::class.java)
            intent.putExtra(EXTRA_START_MAIN, true)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)
        if (savedInstanceState != null) {
            intentLaunched = savedInstanceState.getBoolean(
                EXTRA_INTENT_LAUNCHED,
                false
            )
        }
        viewModel = ViewModelProviders.of(this).get(ProviderViewModel::class.java)
        buttonLayout = findViewById<View>(R.id.buttonLayout) as LinearLayout
        progressBar = findViewById(R.id.progressBar)
        reloadLayout = findViewById(R.id.reloadLayout)
        btnReload = findViewById(R.id.btnReload)
        init()
    }

    private fun init() {
        setUpListeners()
        getProviders()
    }

    private fun getProviders() {
        if (HubIdentityProvider.dataSourceBuildConfig() == null) {
            finish()
            return
        }
        viewModel.getProviders(DataSourceInjection.configureProviderRepository(HubIdentityProvider.dataSourceBuildConfig()!!))
    }

    private fun setUpListeners() {
        btnReload.setOnClickListener {
            reloadLayout.visibility = View.GONE
            getProviders()
        }

        viewModel.loadingObserver.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
                reloadLayout.visibility = View.GONE
                buttonLayout.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                //buttonLayout.visibility = View.VISIBLE
            }

        })

        viewModel.dataObserver.observe(this, Observer {
            showProviders(it)
            buttonLayout.visibility = View.VISIBLE
        })

        viewModel.exceptionObserver.observe(this, Observer {
            //Toast.makeText(this, it ?: "Error", Toast.LENGTH_LONG).show()
            reloadLayout.visibility = View.VISIBLE
        })
    }

    private fun showProviders(providers: List<Provider>) {
        for (provider in providers) {
            val buttonViews =
                LayoutInflater.from(this).inflate(R.layout.social_login_view, null, false)
            val imgLogo: ImageView = buttonViews.findViewById(R.id.imgLogo)
            val txtLabel: TextView = buttonViews.findViewById(R.id.txtLabel)
            when (provider.name) {
                Providers.FACEBOOK.provider_name -> {
                    txtLabel.text =
                        getString(R.string.button_label, Providers.FACEBOOK.label)//provider.name
                    txtLabel.setTextColor(ContextCompat.getColor(this, R.color.white))
                    buttonViews.background =
                        ContextCompat.getDrawable(this, R.drawable.facebook_button_background)
                    imgLogo.setImageResource(R.drawable.ic_facebook)
                }
                Providers.GOOGLE.provider_name -> {
                    txtLabel.text = getString(R.string.button_label, Providers.GOOGLE.label)
                    txtLabel.setTextColor(ContextCompat.getColor(this, R.color.black))
                    buttonViews.background =
                        ContextCompat.getDrawable(this, R.drawable.google_button_background)
                    imgLogo.setImageResource(R.drawable.ic_google)
                }
                Providers.TWITTER.provider_name -> {
                    txtLabel.text =
                        getString(R.string.button_label, Providers.TWITTER.label)
                    txtLabel.setTextColor(ContextCompat.getColor(this, R.color.white))
                    buttonViews.background =
                        ContextCompat.getDrawable(this, R.drawable.twitter_button_background)
                    imgLogo.setImageResource(R.drawable.ic_twitter)
                }
                Providers.LINE.provider_name -> {
                    txtLabel.text =
                        getString(R.string.button_label, Providers.LINE.label)
                    txtLabel.setTextColor(ContextCompat.getColor(this, R.color.white))
                    buttonViews.background =
                        ContextCompat.getDrawable(this, R.drawable.line_button_background)
                    imgLogo.setImageResource(R.drawable.ic_line)
                }
            }
            buttonViews.setOnClickListener {
                /*val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(provider.request_url))
                startActivity(browserIntent)*/
                startActivity(
                    WebViewActivity.newIntent(this, provider.request_url)
                )
            }
            //val buttonLayout = findViewById<View>(R.id.buttonLayout) as LinearLayout
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topMargin = 20
            buttonLayout.addView(buttonViews, layoutParams)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        //buttonLayout.removeAllViews()
        //viewModel.getProviders()
        if (!intentLaunched) {
            intentLaunched = true
            return
        }

        val resultMissing = intent.data == null
        if (resultMissing) {
            setResult(RESULT_CANCELED)
        }
        HubIdentityProvider.resume(intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(
            EXTRA_INTENT_LAUNCHED,
            intentLaunched
        )
    }
}