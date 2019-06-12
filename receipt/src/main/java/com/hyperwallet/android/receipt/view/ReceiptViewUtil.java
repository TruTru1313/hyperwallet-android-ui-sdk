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
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hyperwallet.android.receipt.view;

import static com.hyperwallet.android.model.receipt.Receipt.Entries.CREDIT;
import static com.hyperwallet.android.model.receipt.Receipt.Entries.DEBIT;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hyperwallet.android.common.util.DateUtils;
import com.hyperwallet.android.model.receipt.Receipt;
import com.hyperwallet.android.receipt.R;

import java.util.Locale;

final class ReceiptViewUtil {

    private static final String CAPTION_DATE_FORMAT = "MMMM dd, yyyy";

    private ReceiptViewUtil() {
    }

    static void setTransactionView(@NonNull final Receipt receipt, @NonNull final View view) {
        TextView transactionTypeIcon = view.findViewById(R.id.transaction_type_icon);
        TextView transactionTitle = view.findViewById(R.id.transaction_title);
        TextView transactionDate = view.findViewById(R.id.transaction_date);
        TextView transactionAmount = view.findViewById(R.id.transaction_amount);
        TextView transactionCurrency = view.findViewById(R.id.transaction_currency);

        if (CREDIT.equals(receipt.getEntry())) {
            transactionAmount.setTextColor(transactionAmount.getContext()
                    .getResources().getColor(R.color.positiveColor));
            transactionAmount.setText(transactionAmount.getContext()
                    .getString(R.string.credit_sign, receipt.getAmount()));
            transactionTypeIcon.setTextColor(transactionTypeIcon.getContext()
                    .getResources().getColor(R.color.positiveColor));
            transactionTypeIcon.setBackground(transactionTypeIcon.getContext()
                    .getDrawable(R.drawable.circle_positive));
            transactionTypeIcon.setText(transactionTypeIcon.getContext().getText(R.string.credit));
        } else if (DEBIT.equals(receipt.getEntry())) {
            transactionAmount.setTextColor(transactionAmount.getContext()
                    .getResources().getColor(R.color.colorAccent));
            transactionAmount.setText(transactionAmount.getContext()
                    .getString(R.string.debit_sign, receipt.getAmount()));
            transactionTypeIcon.setTextColor(transactionTypeIcon.getContext()
                    .getResources().getColor(R.color.colorAccent));
            transactionTypeIcon.setBackground(transactionTypeIcon.getContext()
                    .getDrawable(R.drawable.circle_negative));
            transactionTypeIcon.setText(transactionTypeIcon.getContext().getText(R.string.debit));
        }

        transactionCurrency.setText(receipt.getCurrency());
        transactionTitle.setText(getTransactionTitle(receipt.getType(), transactionTitle.getContext()));
        transactionDate.setText(DateUtils.toDateFormat(DateUtils.
                fromDateTimeString(receipt.getCreatedOn()), CAPTION_DATE_FORMAT));
    }

    private static String getTransactionTitle(@NonNull final String receiptType, @NonNull final Context context) {
        String showTitle = context.getResources().getString(R.string.unknown_type);
        int resourceId = context.getResources().getIdentifier(receiptType.toLowerCase(Locale.ROOT), "string",
                context.getPackageName());
        if (resourceId != 0) {
            showTitle = context.getResources().getString(resourceId);
        }

        return showTitle;
    }
}
