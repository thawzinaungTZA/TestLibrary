package jp.co.hivelocity.hudidentity.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.hivelocity.hudidentity.R
import jp.co.hivelocity.hudidentity.app.ui.provider.ProviderActivity

class RedirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redirect)

        val intent = Intent(this, ProviderActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if (getIntent() != null) {
            intent.data = getIntent().data
        }
        startActivity(intent)
        finish()
    }
}