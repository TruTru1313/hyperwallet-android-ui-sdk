/*
 * The MIT License (MIT)
 * Copyright (c) 2019 Hyperwallet Systems Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hyperwallet.android.ui.transfermethod;

import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodFields.TYPE;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.BANK_ACCOUNT;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.BANK_CARD;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.PAPER_CHECK;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.PAYPAL_ACCOUNT;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.PREPAID_CARD;
import static com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodTypes.WIRE_ACCOUNT;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.hyperwallet.android.hyperwallet_ui.R;
import com.hyperwallet.android.model.HyperwalletTransferMethod;
import com.hyperwallet.android.model.HyperwalletTransferMethod.TransferMethodType;

import java.util.Locale;

public class TransferMethodUtils {

    /**
     * Get string resource by TransferMethodType
     *
     * @param context Context
     * @param type    TransferMethodType
     * @return String by the defined resource
     * @throws Resources.NotFoundException if the given ID does not exist.
     */
    public static String getStringResourceByName(@NonNull final Context context,
            @NonNull @TransferMethodType final String type) {
        int resId = context.getResources().getIdentifier(type.toLowerCase(Locale.ROOT), "string",
                context.getPackageName());
        if (resId == 0) {
            resId = context.getResources().getIdentifier(BANK_ACCOUNT.toLowerCase(Locale.ROOT), "string",
                    context.getPackageName());
        }
        return context.getString(resId);
    }

    public static String getStringFontIcon(@NonNull final Context context,
            @NonNull @TransferMethodType final String type) {
        int resId = context.getResources().getIdentifier(type.toLowerCase(Locale.ROOT) + "_font_icon", "string",
                context.getPackageName());
        if (resId == 0) {
            resId = context.getResources().getIdentifier(BANK_ACCOUNT.toLowerCase(Locale.ROOT) + "_font_icon",
                    "string",
                    context.getPackageName());
        }

        return context.getString(resId);
    }

    /**
     * Get title by the {@link HyperwalletTransferMethod.TransferMethodFields#TYPE}
     *
     * @param context        {@link Context}
     * @param transferMethod {@link HyperwalletTransferMethod}
     * @return title or null if a TYPE doesn't match any defined string resources
     */
    @NonNull
    public static String getTransferMethodName(@NonNull final Context context,
            final HyperwalletTransferMethod transferMethod) {
        String transferMethodType = transferMethod.getField(TYPE);
        if (transferMethodType == null) {
            transferMethodType = "";
        }
        return getTransferMethodName(context, transferMethodType);
    }

    /**
     * Get title by the type
     *
     * @param context            {@link Context}
     * @param transferMethodType {@link String}
     * @return title if a TYPE matches to defined {@link TransferMethodType}
     */
    @NonNull
    public static String getTransferMethodName(@NonNull final Context context,
            @NonNull @TransferMethodType final String transferMethodType) {

        String title;

        switch (transferMethodType) {
            case BANK_ACCOUNT:
                title = context.getString(R.string.bank_account);
                break;
            case BANK_CARD:
                title = context.getString(R.string.bank_card);
                break;
            case PAPER_CHECK:
                title = context.getString(R.string.paper_check);
                break;
            case PREPAID_CARD:
                title = context.getString(R.string.prepaid_card);
                break;
            case WIRE_ACCOUNT:
                title = context.getString(R.string.wire_account);
                break;
            case PAYPAL_ACCOUNT:
                context.getString(R.string.paypal_account);
                break;
            default:
                title = transferMethodType.toLowerCase(Locale.ROOT) + context.getString(
                        R.string.not_translated_in_braces);
        }

        return title;
    }
}
