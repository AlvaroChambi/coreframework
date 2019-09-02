package es.developer.achambi.coreframework.ui;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import es.developer.achambi.coreframework.R;

public class FloatingTextButton extends FrameLayout {
    public FloatingTextButton(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public FloatingTextButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FloatingTextButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setText(int resource) {
        TextView text = findViewById(R.id.floating_button_text);
        text.setText( resource );
    }

    private void init( Context context, AttributeSet attributeSet ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.floating_text_button_layout, this);

        if ( attributeSet != null ) {
            TypedArray typedArray =
                    context.obtainStyledAttributes( attributeSet, R.styleable.FloatingTextButton );
            if( typedArray.hasValue( R.styleable.FloatingTextButton_text ) ) {
                String text = typedArray.getString( R.styleable.FloatingTextButton_text );
                TextView textView = findViewById(R.id.floating_button_text);
                textView.setText( text );
            }
            typedArray.recycle();
        }
    }
}
