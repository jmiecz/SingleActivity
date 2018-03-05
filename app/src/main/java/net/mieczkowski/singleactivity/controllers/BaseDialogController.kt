package net.mieczkowski.singleactivity.controllers

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RestoreViewOnCreateController
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler

abstract class BaseDialogController : RestoreViewOnCreateController {

    private val SAVED_DIALOG_STATE_TAG = "android:savedDialogState"

    private var dialog: Dialog? = null
    private var dismissed: Boolean = false

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dialog = onCreateDialog(savedViewState)

        dialog?.let {
            it.ownerActivity = activity!!
            it.setOnDismissListener { dismissDialog() }

            savedViewState?.getBundle(SAVED_DIALOG_STATE_TAG)?.let {bundle ->
                it.onRestoreInstanceState(bundle)
            }
        }

        return View(activity)
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)

        dialog?.onSaveInstanceState()?.let {
            outState.putBundle(SAVED_DIALOG_STATE_TAG, it)
        }
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        dialog?.show()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        dialog?.hide()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        dialog?.setOnDismissListener(null)
        dialog?.dismiss()
        dialog = null
    }

    /**
     * Display the dialog, create a transaction and pushing the controller.
     */
    fun showDialog(targetController: Controller) {
        dismissed = false

        setTargetController(targetController)
        targetController.router.pushController(RouterTransaction.with(this)
                .pushChangeHandler(FadeChangeHandler(false))
                .popChangeHandler(FadeChangeHandler()))
    }

    /**
     * Dismiss the dialog and pop this controller
     */
    fun dismissDialog() {
        if (dismissed) {
            return
        }

        try {
            router.popController(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        dismissed = true
    }

    protected fun getDialog(): Dialog? {
        return dialog
    }

    /**
     * Build your own custom Dialog container such as an [android.app.AlertDialog]
     *
     * @param savedViewState A bundle for the view's state, which would have been created in [.onSaveViewState] or `null` if no saved state exists.
     * @return Return a new Dialog instance to be displayed by the Controller
     */
    protected abstract fun onCreateDialog(savedViewState: Bundle?): Dialog
}