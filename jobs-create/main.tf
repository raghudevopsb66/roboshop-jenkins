resource "jenkins_folder" "folder" {
  count       = length(var.folders)
  name        = element(var.folders, count.index)
  description = element(var.folders, count.index)
}

resource "jenkins_job" "infra-jobs" {
  depends_on = [jenkins_folder.folder]
  count      = length(var.infra-jobs)
  name       = element(var.infra-jobs, count.index)
  folder     = "infrastructure"
  template = templatefile("${path.module}/pipeline-job.xml", {
    git_repo = element(var.infra-jobs, count.index)
  })
}
